%Put the csv of the plotter into a variable
data = csvread("octavePlotter.csv")
%That variable is used to set the x and y values to a separate variables
x = data(:,1)
y = data(:,2)
%Use for loop to add a random number from a range I picked from -30 to 30 and get a new y
for i = 1: length(y)
  y(i) = y(i)+randi([-30 30]);
endfor

%Same as plotter just put the new y values instead
plot(x,y)
grid on
xlabel ("x-Values")
ylabel ("salted y-Values")
title("Salter Graph Using Quadratic Formula")
values = [x,y]
csvwrite("octaveSalter.csv", values)
