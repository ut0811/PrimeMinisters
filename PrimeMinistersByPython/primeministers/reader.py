#! /usr/bin/env python
# -*- coding: utf-8 -*-

import io
import table
import tuple

class Reader(io.IO):
	"""リーダ：総理大臣の情報を記したCSVファイルを読み込んでテーブルに仕立て上げる。"""

	def __init__(self, csv_filename):
		"""リーダのコンストラクタ。"""
		self._csv_filename = csv_filename
		return

	def table(self):
		"""ダウンロードしたCSVファイルを読み込んでテーブルを応答する。"""
		csv_file = self.read_csv(self._csv_filename)
		csv_table = table.Table(csv_file[0])
		csv_file.pop(0)
		for row in csv_file:
			a_list = row.split(",")
			csv_table.add(tuple.Tuple(csv_table.attributes(), a_list))
		return csv_table
