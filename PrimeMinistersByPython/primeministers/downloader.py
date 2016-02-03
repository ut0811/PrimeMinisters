#! /usr/bin/env python
# -*- coding: utf-8 -*-

import os
import shutil
import urllib

import io
import reader

class Downloader(io.IO):
	"""ダウンローダ：総理大臣のCSVファイル・画像ファイル・サムネイル画像ファイルをダウンロードする。"""

	def __init__(self, base_directory):
		"""ダウンローダのコンストラクタ。"""
		self.base_directory = base_directory
		#self.url = 'http://www.cc.kyoto-su.ac.jp/~atsushi/Programs/CSV2HTML/PrimeMinisters'
		#self.filename_of_csv = 'PrimeMinister.csv'
		return

	def download_all(self):
		"""すべて（総理大臣の情報を記したCSVファイル・画像ファイル群・縮小画像ファイル群）をダウンロードし、テーブルを応答する。"""
		self.download_csv()
		a_reader = reader.Reader(self._base_directory+"PrimeMinisters.csv")
		for i in range(39, 63):
			self.download_images(i)
		return a_reader.table()

	def download_csv(self):
		"""総理大臣の情報を記したCSVファイルをダウンロードする。"""
		try: 
			a_filename = urllib.urlopen(self.url + '/' + self.filename_of_csv)
		except URLError, an_exception:  
			print an_exception.reason
		try: 
			with open(self.base_directory + '/' + self.filename_of_csv ,'w') as local_file:
					shutil.copyfileobj(a_filename, local_file)
		except IOError, an_exception:
			print an_exception.reason
		a_filename.close()
		return None

	def download_images(self, image_filenames):
		"""画像ファイル群または縮小画像ファイル群をダウンロードする。"""
# 		http://www.cc.kyoto-su.ac.jp/~atsushi/Programs/CSV2HTML/PrimeMinisters/images/039.jpg
		a_reader = reader.Reader(self.filename_of_csv)
		
		return
