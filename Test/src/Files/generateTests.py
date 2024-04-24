import random
import string

def generate_unique_words(num_lines, min_length=5, max_length=10):
    words = set()
    chars = string.ascii_lowercase  # Use lowercase letters for generating words

    # Generate unique words
    while len(words) < num_lines:
        word_length = random.randint(min_length, max_length)  # Random word length
        word = ''.join(random.choice(chars) for _ in range(word_length))
        words.add(word)

    return words

def write_to_file(words, filename):
    with open(filename, 'w') as f:
        for word in words:
            f.write(f"{word}\n")

if __name__ == "__main__":
    num_lines = 1000000000  # Number of lines (words) in the file
    output_file = "unique_words_3.txt"

    unique_words = generate_unique_words(num_lines)
    write_to_file(unique_words, output_file)

    print(f"File '{output_file}' created with {num_lines} unique words.")
