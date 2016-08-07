fix :: (a -> a)-> a
fix g = g (fix g)

c :: Int -> Int
c = \x -> 3
