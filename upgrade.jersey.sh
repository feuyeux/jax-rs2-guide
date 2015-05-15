sed -i "" 's/jersey.version>2.[0-9]/jersey.version>2.17/g' `grep 'jersey.version>' -rl ./sample`
