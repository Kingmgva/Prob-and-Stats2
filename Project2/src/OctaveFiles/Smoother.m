saltedData = csvread("octaveSalter.csv")

x = saltedData(:,1)
y = saltedData(:,2)

smoothedY = y
windowVal = 3
smoothingTimes = 3

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

plot(x, smoothedY)
grid on
xlabel = ("x-Values")
ylabel = ("Smoothed y-Values")
title = ("Smoothed Graph of Salted Data")
values = [x, smoothedY]
csvwrite("octaveSmoother.csv", values)
