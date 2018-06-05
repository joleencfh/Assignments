import timeit
import sys
import datetime
import itertools


def foursum_slow(a, prt=False):
  n = len(a)
  count = 0
  for i in range(n):
    for j in range(i+1, n):
      for k in range(j+1, n):
        for l in range (k+1, n):
         if sum([a[i], a[j], a[k], a[l]]) == 0:

          count += 1

          if prt:
            sys.stdout.write('{:7d} + {:7d} + {:7d}+ {:7d}\n'.format(a[i], a[j], a[k], a[l]))
  return count

def count_fast(a):
  """FourSum using itertools: see AlgsSedgewickWayne threesum.py"""
  return sum((1 for x in itertools.combinations(a, r=4) if not sum(x)))

def count_faster(a):
  """FourSum using itertools: see AlgsSedgewickWayne threesum.py"""
  return sum(1 for x, y, z, w in itertools.combinations(a, r=4) if x+y==-z-w)

def run_faster (a, cnt_fnc=count_faster):
    """this runs foursum slow and returns True if any foursum=0 is found"""
    count= cnt_fnc(a)
    if count>0:
      print("True")
    elif count==0:
      print("False")


def timing_slow (a, cnt_fnc=foursum_slow):
    """this runs foursum slow and reports the elapsed time."""
    tic = timeit.default_timer()
    counter = 10
    while counter>0:
      cnt_fnc=(a)
      counter-=1
    sys.stdout.write("Elapsed time: {}\n".format(
    str(datetime.timedelta(seconds=(timeit.default_timer()-tic)))))



if __name__ == '__main__':
  import os
  from random import randrange

myList = []
counter = (int(input()))
while counter>0:
    num = input()
    myList.append(int(num))
    counter-=1

run_faster(myList)
