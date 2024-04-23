import matplotlib.pyplot as plt
import numpy as np
from matplotlib.table import Table

def read_double_data_from_file(filename, input_sizes):
    data = []
    with open(filename, 'r') as file:
        for line in file:
            # Split each line into a list of floats
            numbers = list(map(float, line.strip().split()))
            data.append(numbers)
    interpolated_data = []
    for numbers in data:
        interpolated_numbers = np.interp(input_sizes, range(1, len(numbers) + 1), numbers)
        interpolated_data.append(interpolated_numbers)
    
    return interpolated_data

def plot_interpolated_double_data(data, input_sizes):
    table_sizes = np.arange(1000, 5001, 500)  # Example table initial sizes (replace with your actual sizes)
    
    fig, ax = plt.subplots()

    # Plot each set of interpolated numbers against input sizes
    for i, numbers in enumerate(data):
        ax.plot(input_sizes, numbers, label=f'Table initial size {table_sizes[i]}')  # Plot each set of numbers against input sizes
    
    ax.set_xlabel('Input Size')
    ax.set_ylabel('Average Number Of Collisions')
    ax.set_title('O(N) method')
    ax.legend()
    ax.grid(True)

    # Create a table outside the plot to display table initial sizes
    table_data = [[f'Table initial size {size}'] for size in table_sizes]
    table = Table(ax, bbox=[1.1, 0, 0.3, 1.0])  # Adjust bbox for table position and size
    table.auto_set_font_size(False)
    table.set_fontsize(10)
    table.scale(1.2, 1.2)  # Adjust scaling of the table for better readability

    # Add table initial sizes to the table
    for i, (size,) in enumerate(table_data):
        table.add_cell(i, 0, width=1, height=1, text=size, loc='left', edgecolor='none')

    ax.add_table(table)

    plt.show()


filename = 'src\\data3ON.txt'
input_sizes = np.arange(400, 4001, 500)
data = read_double_data_from_file(filename, input_sizes)    
plot_interpolated_double_data(data, input_sizes)
