FROM node:14

# Install Firebase CLI
RUN npm install -g firebase-tools

# Create working directory
WORKDIR /usr/src/app

# Expose necessary ports
EXPOSE 4000 9005 8080

# Command to start Firebase emulator
CMD ["firebase", "emulators:start"]