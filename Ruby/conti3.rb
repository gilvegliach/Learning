require 'tk'
root = TkRoot.new() { title "Conti" }

values    = TkFrame.new(root).pack("side"=>"top")
first       = TkFrame.new(values).pack("side"=>"left")
lFirst      = TkLabel.new(first){ text "Alis"}.pack("side"=>"top")
second   = TkFrame.new(values).pack("side"=>"left")
lsecond   = TkLabel.new(second){ text "Enzo"}.pack("side"=>"top")
third       = TkFrame.new(values).pack("side"=>"left")
lthird      = TkLabel.new(third){ text "Gil"}.pack("side"=>"top")
#fourth     = TkFrame.new(values).pack("side"=>"left")
#lfourth      = TkLabel.new(fourth){ text "Max"}.pack("side"=>"top")
firstValues = []
secondValues = []
thirdValues = []
#fourthValues = []
#list = TkListbox.new(root).pack("side"=>"left")
#bar = TkScrollbar.new(root).pack('side'=>'right', 'fill'=>'y')

30.times { |i|
   firstValues << TkEntry.new(first).pack("side"=>"top", "fill"=>"x")
   secondValues << TkEntry.new(second).pack("side"=>"top", "fill"=>"x")
   thirdValues << TkEntry.new(third).pack("side"=>"top", "fill"=>"x")
  # fourthValues << TkEntry.new(fourth).pack("side"=>"top", "fill"=>"x")
  # firstValues <<  entry
}

labels = TkFrame.new(root).pack("side"=>"left")
mFirst = TkLabel.new(labels) { text "Alis:" }.pack("side"=>"top")
mSecond = TkLabel.new(labels) { text "Enzo:" }.pack("side"=>"top")
mThird = TkLabel.new(labels) { text "Gil:" }.pack("side"=>"top")
#mFourth = TkLabel.new(labels) { text "Max:" }.pack("side"=>"left")

#list.yscrollbar(bar)

button = TkButton.new(root) {
  text "Calcola"
  command proc {
    firstMoney = secondMoney = thirdMoney = fourthMoney = 0.0
    
    firstValues.each{ |entry|
       firstMoney += entry.value.to_f
    }
    
    secondValues.each{ |entry|
       secondMoney += entry.value.to_f
    }
    
    thirdValues.each{ |entry|
       thirdMoney += entry.value.to_f
    }
    
 #   fourthValues.each{ |entry|
 #      fourthMoney += entry.value.to_f
 #   }
    #p firstMoney    
    money = (firstMoney + secondMoney + thirdMoney) / 3.0 #+ fourthMoney) / 4.0
    
    mFirst.text = "Alis: " + (firstMoney - money).to_s
    mSecond.text = "Enzo: " + (secondMoney - money).to_s
    mThird.text = "Gil: " + (thirdMoney - money).to_s
  #  mFourth.text = "Max: " + (fourthMoney - money).to_s
  }
}
button.configure('underline'=>0)
button.pack()
Tk.mainloop