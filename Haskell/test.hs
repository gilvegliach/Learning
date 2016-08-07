maxi :: (Int, Int) -> Int
maxi (x,y) | x >= y  = x
           | otherwise = y

fact :: Int -> Int
fact 0 = 1
fact (x+1) = (x+1)*(fact x)
fact x = -1

len :: [a] -> Int
len [] = 0
len (x:xs) = 1 + len(xs)

plus :: Int -> Int -> Int
plus x y = x + y

suc :: Int -> Int
suc = plus 1

square :: Float -> Float
square x = x*x

-- :t square per valutare solo il tipo dell'expr

data MyBoole = MyTrue | MyFalse deriving Show

mynot :: MyBoole -> MyBoole
mynot MyTrue = MyFalse
mynot _ = MyTrue

data Nats = Zero | Succ Nats  -- deriving Show

natsInt :: Nats -> Int
natsInt Zero = 0
natsInt (Succ x) = 1 + natsInt x

instance Show Nats where
  show x = show (natsInt x)

nplus :: Nats -> Nats -> Nats
nplus Zero x = x
nplus (Succ x) y = Succ (nplus x y)

from :: Num a => a -> [a]
from x = x : from (x+1)

primes :: [Integer]
primes = sieve [2..]
   where sieve (p:xs) = p : sieve [x | x<-xs, x `mod` p /= 0]
