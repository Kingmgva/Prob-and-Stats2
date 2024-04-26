x = -10:1:20
y = x .^2 + 3*x + 5
plot(x,y);
grid on
xlabel ("x-Values")
ylabel ("y-Values")
title("Plotter Graph Using Quadratic Formula")
values = [x',y']
csvwrite("octavePlotter.csv", values)
