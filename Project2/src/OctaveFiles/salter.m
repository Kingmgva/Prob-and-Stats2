data = csvread("octavePlotter.csv")

x = data(:,1)
y = data(:,2)

for i = 1: length(y)
  y(i) = y(i)+randi([-30 30]);
endfor

plot(x,y)
grid on
xlabel ("x-Values")
ylabel ("salted y-Values")
title("Salter Graph Using Quadratic Formula")
values = [x,y]
csvwrite("octaveSalter.csv", values)
