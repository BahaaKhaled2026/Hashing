import numpy as np
import matplotlib.pyplot as plt

# Given data as a string
data_str = """
0 0 2 0 0 0 0 0 2 3 2 3 5 4 3 5 6 5 7 10 9 9 7 10 13 10 10 17 13 15  
0 0 0 0 0 1 2 0 1 1 3 5 4 4 3 3 6 5 9 7 8 5 11 7 5 8 10 12 10 15 
0 0 0 1 6 2 0 1 6 4 1 2 3 1 3 7 8 11 5 13 12 8 6 5 11 20 10 13 25 13 
0 0 2 0 0 3 2 2 2 2 7 2 3 9 7 7 7 10 6 10 8 6 10 6 17 14 11 10 16 21 
0 1 0 0 1 0 5 3 1 3 6 3 4 4 0 2 8 8 6 9 11 10 15 13 27 12 11 19 9 7 
"""

# Parse the data string into a 2D array (matrix)
data_lines = data_str.strip().split('\n')
data_matrix = [list(map(int, line.split())) for line in data_lines]

# Define starting table sizes (y-axis) and input sizes (x-axis)
starting_table_sizes = list(range(1000, 5500, 500))  # 200, 600, 1000, 1400, 1800
input_sizes = list(range(50, 1550, 50))  # 50, 100, 150, ..., 1000

# Create a figure and axis for plotting
plt.figure(figsize=(10, 6))

# Plot each row of data as a separate line with different colors
for i, row in enumerate(data_matrix):
    plt.plot(input_sizes, row, label=f'Starting Size: {starting_table_sizes[i]}')

# Set labels and title
plt.xlabel('Input Size')
plt.ylabel('Time ms')
plt.title('O(n^2) single insertion')

# Show legend with labels corresponding to starting table sizes
plt.legend()

# Show plot
plt.grid(True)
plt.show()
