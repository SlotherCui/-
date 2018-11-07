# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class GameprojectItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()

    game_name = scrapy.Field()
    game_type =  scrapy.Field()
    game_producers = scrapy.Field()
    game_played = scrapy.Field()
    game_wanne_paly = scrapy.Field()
    game_tag = scrapy.Field()
    game_ign = scrapy.Field()
    pass
