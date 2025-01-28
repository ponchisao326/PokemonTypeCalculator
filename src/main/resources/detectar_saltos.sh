#!/bin/bash

# Function to update non-sequential IDs
update_non_sequential_ids() {
    local input_file="$1"
    local output_file="$2"
    local previous_id=0
    local current_id=0
    local line_number=0

    echo "Updating non-sequential IDs in $input_file..."

    # Create or clear the output file
    > "$output_file"

    # Read the file line by line
    while IFS=, read -r id rest; do
        line_number=$((line_number + 1))

        # Skip the header if present (assuming the first line is a header)
        if [ "$line_number" -eq 1 ]; then
            echo "$id,$rest" >> "$output_file"  # Write header to output
            previous_id="$id"
            continue
        fi

        # Convert ID to integer
        current_id=$(echo "$id" | tr -d '[:space:]')  # Remove whitespace

        # Check for non-sequential IDs
        if [ "$previous_id" -ne 0 ] && [ "$current_id" -ne $((previous_id + 1)) ]; then
            echo "Updating ID from $current_id to $((previous_id + 1)) at line $line_number"
            current_id=$((previous_id + 1))  # Update current ID to fill the gap
        fi

        # Write the updated line to the output file
        echo "$current_id,$rest" >> "$output_file"

        # Update previous_id
        previous_id="$current_id"
    done < "$input_file"
}

# Example usage
input_file="pokemon_base_stats.csv"
output_file="updated_pokemon_base_stats.csv"
update_non_sequential_ids "$input_file" "$output_file"