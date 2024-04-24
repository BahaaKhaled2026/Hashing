import matplotlib.pyplot as plt

# Given data as a single line string
data_str = "0 0 1 0 0 0 0 0 0 2 0 0 0 0 0 0 2 0 2 2 1 0 0 0 0 2 0 2 0 0 2 0 0 4 0 2 0 0 0 4"

# Split the string into a list of numbers
data = list(map(int, data_str.split()))

# Define x-axis values (500 to 5000 with step 200)
x_values = list(range(500, 20001, 500))

# Create a plot
plt.figure(figsize=(10, 6))
plt.plot(x_values, data, marker='o', linestyle='-', color='b')

# Set labels and title
plt.xlabel('Input Size')
plt.ylabel('Rebuild Time ms')
plt.title('O(n) Batch Insertion')

# Customize x-axis ticks to show every 400 units
plt.xticks(range(500, 20500, 1000))

# Show grid
plt.grid(True)

# Show legend
plt.legend()

# Display the plot
plt.show()
