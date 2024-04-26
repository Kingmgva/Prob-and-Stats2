x = -10:1:20
y = x .^2 + 3*x + 5
%Much easier to put data into the csv compared to java
plot(x,y); %plots the x and y into a graph
grid on %makes lines visible on graph so you can see points it is crossing
%Labels the x and y axis and gives graph a title
xlabel ("x-Values")
ylabel ("y-Values")
title("Plotter Graph Using Quadratic Formula")
%Put the x and y into a values variable to make it easier to input into csv in the correct format
values = [x',y']
csvwrite("octavePlotter.csv", values)
