# -*- coding: utf-8 -*-
import csv

import scrapy
from ..items import GameprojectItem

class GameskySpiderSpider(scrapy.Spider):



    name = 'gamesky_spider'
    allowed_domains = ['ku.gamersky.com']
    # start_urls = []

    def __init__(self, category=None, *args, **kwargs):


        super(GameskySpiderSpider, self).__init__(*args, **kwargs)

        with open('D:/pythonworkspace/test/taobao/gamedata.csv', 'r') as f:
            reader = csv.reader(f)
            for row in reader:
                if row[1] != "--" and row[1] != "score":
                    self.start_urls.append(row[2])


    def parse(self, response):
        # game_list = response.xpath("//div[@class='Mid']//ul/li")
        # print(game_list)
        #
        #
        # for i_item in game_list:
        #     game_item = GameprojectItem()
        #     game_item['game_name'] = i_item.xpath(".//div[@class='img']//a/p/text()").extract_first()
        #     game_item['game_score'] = i_item.xpath(".//div[@class='num']/text()").extract_first()
        #     print(game_item)
        game_item = GameprojectItem()
        game_item['game_name'] = response.xpath("//div[@class='tit_CH']/text()").extract_first()
        game_item['game_type'] = response.xpath("// div[@class ='tag']/a/text()").extract_first()
        game_item['game_producers'] = response.xpath("// div[@class ='div3']//div[@class='tt2']//div[@class='txt']/text()").extract_first()
        game_item['game_played'] = response.xpath("// span[@class ='num1']/text()").extract_first()
        game_item['game_wanne_paly'] = response.xpath("// span[@class ='num2']/text()").extract_first()
        # tag_list = response.xpath("//span[@class ='tag-con']/a")
        # for tag_item in tag_list:
        #     print(tag_item.xpath("/text()"))
        # game_item['game_tag'] = response.xpath("// span[@class ='cur']//a/")
        print(game_item)