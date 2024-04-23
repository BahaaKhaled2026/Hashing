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
        ax.plot(input_sizes, numbers)  # Plot each set of numbers against input sizes
    
    ax.set_xlabel('Input Size')
    ax.set_ylabel('Average Number Of Collisions')
    ax.set_title('O(N) method')
    ax.legend()
    ax.grid(True)

    plt.show()


# filename = 'src\\data3ON.txt'
ON_SINGLE_SPACE = 'A:\\university\\year 2\\Second semester\\Data structure 2\\Labs\\lab 2\\Hashing\\DataAnalysis\\src\\Data\\singleInsertONSpace.txt'
ON_SINGLE_TIME='A:\\university\\year 2\\Second semester\\Data structure 2\\Labs\\lab 2\\Hashing\\DataAnalysis\\src\\Data\\singleInsertONTime.txt'
ON_SINGLE_COLLISIONS='A:\\university\\year 2\\Second semester\\Data structure 2\\Labs\\lab 2\\Hashing\\DataAnalysis\\src\\Data\\singleInsertON.txt'
ON_SINGLE_BUILD_TIME='A:\\university\\year 2\\Second semester\\Data structure 2\\Labs\\lab 2\\Hashing\\DataAnalysis\\src\\Data\\singleInsertONRebuildTime'

# ON_BATCH_SPACE='A:\\university\\year 2\\Second semester\\Data structure 2\\Labs\\lab 2\\Hashing\\DataAnalysis\\src\\Data\\BatchInsertONSpace.txt'
# ON_BATCH_TIME='A:\\university\\year 2\\Second semester\\Data structure 2\\Labs\\lab 2\\Hashing\\DataAnalysis\\src\\Data\\BatchInsertONTime.txt'
# ON_BATCH_COLLISIONS='A:\\university\\year 2\\Second semester\\Data structure 2\\Labs\\lab 2\\Hashing\\DataAnalysis\\src\\Data\\BatchInsertON.txt'
# ON_BATCH_BUILD_TIME='"A:\\university\\year 2\\Second semester\\Data structure 2\\Labs\\lab 2\\Hashing\\DataAnalysis\\src\\Data\\BatchInsertONRebuildTime.txt"'

# ON2_SINGLE_SPACE = 'singleInsertON2Space.txt'
# ON2_SINGLE_TIME='singleInsertON2Time.txt'
# ON2_SINGLE_COLLISIONS='singleInsertON2.txt'
# ON2_SINGLE_BUILD_TIME='singleInsertON2ReuildTime.txt'

# ON2_BATCH_SPACE='BatchInsertON2Space.txt'
# ON2_BATCH_TIME='BatchInsertON2Time.txt'
# ON2_BATCH_COLLISIONS='BatchInsertON2.txt'
# ON2_BATCH_BUILD_TIME='BatchInsertON2ReuildTime.txt'




input_sizes = np.arange(400, 4001, 500)
batch_data = np.arange(400,20001,1000)
single_insertion = np.arange(50,501,50)

ON_collisions = read_double_data_from_file(ON_SINGLE_COLLISIONS, input_sizes)
ON_space = read_double_data_from_file(ON_SINGLE_SPACE, input_sizes)
ON_time = read_double_data_from_file(ON_SINGLE_TIME, input_sizes)
ON_build_time = read_double_data_from_file(ON_SINGLE_BUILD_TIME, input_sizes)

# ON_batch_collisions = read_double_data_from_file(ON_BATCH_COLLISIONS, input_sizes)
# ON_batch_space = read_double_data_from_file(ON_BATCH_SPACE, input_sizes)
# ON_batch_time = read_double_data_from_file(ON_BATCH_TIME, input_sizes)
# ON_batch_build_time = read_double_data_from_file(ON_BATCH_BUILD_TIME, input_sizes)

plot_interpolated_double_data(ON_collisions[0], input_sizes)
plot_interpolated_double_data(ON_space[0], input_sizes)
plot_interpolated_double_data(ON_time[0], input_sizes)
plot_interpolated_double_data(ON_build_time[0], input_sizes)

# plot_interpolated_double_data(ON_batch_collisions, input_sizes)
# plot_interpolated_double_data(ON_batch_space, input_sizes)
# plot_interpolated_double_data(ON_batch_time, input_sizes)
# plot_interpolated_double_data(ON_batch_build_time, input_sizes)