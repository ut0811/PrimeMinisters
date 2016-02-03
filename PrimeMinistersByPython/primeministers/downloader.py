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
		self._base_directory = base_directory
		self._url = 'http://www.cc.kyoto-su.ac.jp/~atsushi/Programs/CSV2HTML/PrimeMinisters/'
		self._filename_of_csv = 'PrimeMinisters.csv'
		
		return

	def download_all(self):
		"""すべて（総理大臣の情報を記したCSVファイル・画像ファイル群・縮小画像ファイル群）をダウンロードし、テーブルを応答する。"""
		images_directory = self._base_directory + 'images'
		if os.path.isdir(images_directory):
			shutil.rmtree(images_directory)
		os.makedirs(images_directory)
		
		thumbnails_directory = self._base_directory + 'thumbnails'
		if os.path.isdir(thumbnails_directory):
			shutil.rmtree(thumbnails_directory)
		os.makedirs(thumbnails_directory)

		self.download_csv()
		a_reader = reader.Reader(self._base_directory + self._filename_of_csv)
		a_table = a_reader.table()
		
		images_names = a_table.image_filenames()
		self.download_images(images_names)
		thumbnails_names = a_table.thumbnail_filenames()
		self.download_images(thumbnails_names)
		
		return a_table

	def download_csv(self):
		"""総理大臣の情報を記したCSVファイルをダウンロードする。"""
		try: 
			a_url = urllib.urlopen(self._url + self._filename_of_csv)
		except URLError, an_exception:  
			print an_exception.reason
		try: 
			with open(self._base_directory + self._filename_of_csv ,'w') as a_file:
					shutil.copyfileobj(a_url, a_file)
		except IOError, an_exception:
			print an_exception.reason
		a_url.close()
		
		return

	def download_images(self, image_filenames):
		"""画像ファイル群または縮小画像ファイル群をダウンロードする。"""
		for filename in image_filenames:
			try:
				a_url = urllib.urlopen(self._url + filename)
				print self._url + filename
			except URLError, an_exception:
				print an_exception.reason
			try:
				with open(self._base_directory + filename,'w') as a_file:
					shutil.copyfileobj(a_url, a_file)
			except IOError, an_exception:
				print an_exception.reason
			a_url.close()

		return
