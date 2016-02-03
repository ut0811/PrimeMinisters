#! /usr/bin/env python
# -*- coding: utf-8 -*-

class Tuple(object):
	"""タプル：総理大臣の情報テーブルの中の各々のレコード。"""

	def __init__(self, attributes, values):
		"""属性リストと値リストからタプルを作るコンストラクタ。"""
		self._attributes = attributes
		self._values = values
		return

	def __str__(self):
		"""自分自身を文字列にして、それを応答する。"""
		a_string = self.__class__.__name__
		a_string += " = "
		a_string += "\m\tvaluas -"
		for a_value in self.values():
			a_string += a_value + ","
		return a_string

	def attributes(self):
		"""属性リストを応答する。"""
		return self._attributes

	def values(self):
		"""値リストを応答する。"""
		return self._values

	def set_values(self, values):
		"""値リストを設定する。"""
		self._values.extend(values)
		return
