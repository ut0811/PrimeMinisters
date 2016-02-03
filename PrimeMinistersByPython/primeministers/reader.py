#! /usr/bin/env python
# -*- coding: utf-8 -*-

import io
import table
import tuple

import csv
import codecs

class Reader(io.IO):
	"""リーダ：総理大臣の情報を記したCSVファイルを読み込んでテーブルに仕立て上げる。"""

	def __init__(self, csv_filename):
		"""リーダのコンストラクタ。"""
		self._csv_filename = csv_filename
		return

	def table(self):
		"""ダウンロードしたCSVファイルを読み込んでテーブルを応答する。"""
		csv_table = table.Table('input')
		is_first = True
		with open(self._csv_filename,'rU') as aFile:
			reader = csv.reader(aFile)
			for row in reader:
				csv_table.add(tuple.Tuple(csv_table.attributes(), row))
		return csv_table
