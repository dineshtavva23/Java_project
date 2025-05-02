import sys
import subprocess
import os

os.makedirs("build", exist_ok=True)

compileJava = subprocess.run(["javac","-d","build","./arbitraryarithmetic/AFloat.java","./arbitraryarithmetic/AInteger.java","./MyInfArith.java"],text=True)

if compileJava.returncode==0 :
    print("Compiled Successfully")
else:
    print("Compilation failed")
    exit(1)

jar_file = "build/aarithmetic.jar"
jar_command = ["jar", "cf", jar_file, "-C", "build", "."]  
createJar = subprocess.run(jar_command, text=True)

if createJar.returncode == 0:
    print(f"JAR created successfully at {jar_file}")
else:
    print("JAR creation failed")
    exit(1)



if len(sys.argv[1:])!=0:
    print("Running MyInfArith as arguments were provided")
    runjava = subprocess.run(["java","-cp","build","MyInfArith"]+sys.argv[1:],text=True)

