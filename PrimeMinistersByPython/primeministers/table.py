#! /usr/bin/env python
# -*- coding: utf-8 -*-

import attributes

class Table(object):
	"""表：総理大臣の情報テーブル。"""

	def __init__(self, kind_string):
		"""テーブルのコンストラクタ。"""
		self._attributes = attributes.Attributes(kind_string)
		self._tuples = []
		return

	def __str__(self):
		"""自分自身を文字列にして、それを応答する。"""
		res = self.__class__.__name__
		res += " = "
		res += "\n"+self._attributes.__str__()
		for var in self._tuples:
			res += "\n"+var.__str__()
		return res

	def add(self, tuple):
		"""タプルを追加する。"""
		self._tuples.append(tuple)
		return

	def attributes(self):
		"""属性リストを応答する。"""
		return self._attributes

	def image_filenames(self):
		"""画像ファイル群をリストにして応答する。"""
		return None

	def thumbnail_filenames(self):
		"""縮小画像ファイル群をリストにして応答する。"""
		return None
	
	def tuples(self):
		"""タプル群を応答する。"""
		return self._tuples
