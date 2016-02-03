#! /usr/bin/env python
# -*- coding: utf-8 -*-

class Attributes(object):
	"""属性リスト：総理大臣の情報テーブルを入出力する際の属性情報を記憶。"""

	def __init__(self, kind_string):
		"""入力用("input")または出力用("output")で属性リストを作成するコンストラクタ。"""
		self._keys = []
		self._names = []
		
		if kind_string == 'input':
			self._keys = ['no','order','names','kana','period','school','party','birth','image','thumbnail']
			self._names = ['人目','代','氏名','ふりがな','在位期間','出身校','政党','出身地','画像','サムネイル']
		elif kind_string == 'output':
			self._keys = ['no','order','names','kana','period','day','school','party','birth','image']
			self._names = ['人目','代','氏名','ふりがな','在位期間','在位日数','出身校','政党','出身地','画像']
		else:
			print '不正な値です'

		return

	def __str__(self):
		"""自分自身を文字列にして、それを応答する。"""
		a_string = self.__class__.__name__
		a_string += " = "
		a_string += "\n\tkeys -"
		for a_key in self.keys():
			a_string += a_key + ", "
		a_string += "\n\tnames - "
		for a_name in self.names():
			a_string += a_name + ", "
		return a_string

	def keys(self):
		"""キー群を応答する。"""
		return self._keys

	def names(self):
		"""名前群を応答する。"""
		return self._names

	def set_names(self, names):
		"""名前群を設定する。"""
		self._names.extend(names)
		return
