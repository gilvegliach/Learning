factn = Thread.new(10000) do |n|
	t = Thread.current
	t[:fact] = 1;
	t[:n]     = 1;
	1.upto(n) { |i| t[:fact] *= i; t[:n] += 1 }
        puts "factorial of #{n} is #{t[:fact]}"
end

sleep 0.001
if factn.alive? then
	factn.kill
	puts "factn stopped!"
	puts( "The computation of factn up to #{factn[:n]} is #{factn[:fact]}")
end

	
	