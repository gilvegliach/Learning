
def prime(n)
   if(n < 2) then return true end
   i = 2
   while(i <= Math.sqrt(n)) do
       if( (n % i) == 0) then return false
       end
       i += 1
   end
   return true
  
end

def gcd(a, b)
  if( a % b == 0) then return b end
  return gcd(b, a%b)
end