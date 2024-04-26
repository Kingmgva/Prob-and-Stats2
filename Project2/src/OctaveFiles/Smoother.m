%The calls and variables stay the same just added more variables for window value, and how many times the data gets smoothed.
%Make a variable that is the same as y that is put into the loop because the values inside get updated everytime the loop occurs
saltedData = csvread("octaveSalter.csv")

x = saltedData(:,1)
y = saltedData(:,2)

smoothedY = y
windowVal = 3
smoothingTimes = 3

%Same formula from the one in Java just translated in the format for octave
for i = 1:smoothingTimes
  for j = 1:length(y)
    count = 0
    avgTotal = 0
    for k = j-windowVal:j+windowVal
      if(k > 0 && k <= length(y))
        avgTotal = avgTotal + smoothedY(k)
        count = count + 1
      endif
    endfor
    smoothedY(j) = round(avgTotal/count)
  endfor
endfor
%Same as the other two just with the new y value and updated names
plot(x, smoothedY)
grid on
xlabel = ("x-Values")
ylabel = ("Smoothed y-Values")
title = ("Smoothed Graph of Salted Data")
values = [x, smoothedY]
csvwrite("octaveSmoother.csv", values)
