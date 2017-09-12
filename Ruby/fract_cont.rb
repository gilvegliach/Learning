#puts "Write a number > 1"
#n = gets
#n = n.to_i




def frac(a)
  case a.length  
    when 1
      print a[0]
      return a[0]
    when 2
      printf "%d*%d+1", a[0], a[1]
      return a[0]*a[1]+1
    else
      printf "%d*(", a[0]
      n = a[0] * frac(Array(a[1, a.length]))
      printf ")+"
      n += frac(Array(a[2, a.length]))
      return n
  end
  
end

#puts "\n" + frac([2, 1, 4, 2]).to_s 

def make_num(n, d)
    if( n % d < 2) then return [n/d, d]
    end
    return ([] << n/d) + make_num(d, n % d)
  
end

def make_den(n, d)
    a = make_num(n, d)
    return a[1, a.length]
end
#puts make_frac(67, 24)
print make_num(67,24)
print "/"
print make_den(67,24)