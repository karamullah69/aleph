# Use an official Drupal image
FROM drupal:latest

# Copy your Drupal app files
COPY . /var/www/html

# Set environment variables
ENV COMMON_DATA=HelloWorld

# Additional configuration if needed
