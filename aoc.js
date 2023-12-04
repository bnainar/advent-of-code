const { exec } = require('child_process');



// Function to execute Java class
function runJavaClass() {
    const randomPort = Math.floor(60000 + Math.random() * 5535);
    let command = " aoc" + process.argv[2] + ".Day" + process.argv[3];
    console.log(command);
  // console.log(`Running Java class: AOC${process.argv[2]} Day${process.argv[3]}`);
  exec(command, (error, stdout, stderr) => {
    if (error) {
      console.error(`Error: ${error.message}`);
      return;
    }
    if (stderr) {
      console.error(`stderr: ${stderr}`);
      return;
    }
    console.log(`stdout: \n${stdout}`);
  });
}

// Run the Java class every second
setInterval(runJavaClass, 1000);
