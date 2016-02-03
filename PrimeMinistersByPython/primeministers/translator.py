#! /usr/bin/env python
# -*- coding: utf-8 -*-

import datetime
import os
import re

import table
import tuple

import csv

class Translator(object):
	"""トランスレータ：総理大臣のCSVファイルをHTMLページへと変換するプログラム。"""

	def __init__(self, input_table):
		"""トランスレータのコンストラクタ。"""
		self._input_table = input_table
		self._output_table = table.Table('output')
		return

	def compute_string_of_days(self, period):
		"""在位日数を計算して、それを文字列にして応答する。"""
		
		if period == '在位期間': return '在位日数'
		days = re.split(r'[〜年月日]*', period)
		days.pop()
		days = map(int, days)
		
		from_day = datetime.date(days[0], days[1], days[2])
		if len(days) > 4:
			to_day = datetime.date(days[3], days[4], days[5])
		else:
			d = datetime.datetime.today()
			to_day = datetime.date(d.year, d.month, d.day)
		diff = to_day - from_day
		
		return str(diff.days)

	def compute_string_of_image(self, tuple):
		"""サムネイル画像から画像へ飛ぶためのHTML文字列を作成して、それを応答する。"""
		
		keys = tuple.attributes().keys()
		values = tuple.values()
		a_no = values[keys.index('no')]
		a_image = values[keys.index('image')]
		a_thumbnail = values[keys.index('thumbnail')]
		imageTag = "<a name="+a_no+" href="+a_image+"><img class=\"borderless\" src="+a_thumbnail+" width=\"25\" height=\"32\" alt="+a_no+".jpg></a>"
		
		return imageTag

	def table(self):
		"""総理大臣のCSVファイルを基にしたテーブルから、HTMLページを基にするテーブルに変換して、それを応答する。"""
		html_table = table.Table('output')
		html_at = html_table.attributes()
		tuples = self._input_table.tuples()
		is_first = True
		
		for a_tuple in tuples:
			if is_first:
				is_first = False
			else:
				values = a_tuple.values()
				keys = a_tuple.attributes().keys()
				attributes = a_tuple.attributes()
				output = []
				output.append(values[keys.index('no')])
				output.append(values[keys.index('order')])
				output.append(values[keys.index('names')])
				output.append(values[keys.index('kana')])
				output.append(values[keys.index('period')])
				output.append(self.compute_string_of_days(values[keys.index('period')]))
				output.append(values[keys.index('school')])
				output.append(values[keys.index('party')])
				output.append(values[keys.index('birth')])
				output.append(self.compute_string_of_image(a_tuple))

				output_tuple = tuple.Tuple(html_at, output)
				self._output_table.add(output_tuple)
		
		return self._output_table
