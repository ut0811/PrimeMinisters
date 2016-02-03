import csv
filename = 'PrimeMinisters.csv'
with open(filename, 'rb') as f:
	reader = csv.reader(f)
	for row in reader:
		print row
