#!/bin/sh 
basePath=/home/ap/${user}/
srcPath=/home/ap/${user}/src
proPath=/home/ap/${user}/src/BUSI/ablink/DEMO
renameProPath=/home/ap/${user}/src/BUSI/ablink/${proName}
caPath=/home/ap/${user}/src/BUSI/ablink/${proName}/DEMOFnt_HTTP_CLI
renameCaPath=/home/ap/${user}/src/BUSI/ablink/${proName}/${caName}

time=$(date "+%Y%m%d-%H%M%S")

function check_dir(){
	if [ ! -d $1 ];then
		echo dir $1 is not exeist
		return 0
	fi 
	return 1
}

function check_dir_readable(){
	if [ ! -r "$myPath"]; then 
		echo dir $1 is not readable
		return 0
	fi 
	return 1
}


//查看环境变量是否已经生效




ret=check_dir $basePath
if [ 0 eq $ret]; then 
		return 0
fi 


ret=check_dir $srcPath
if [ 0 eq $ret]; then 
		return 0
fi

ret=check_dir $caPath
if [ 0 eq $ret]; then 
		return 0
fi

ret=check_dir $caPath
if [ 0 eq $ret]; then 
		return 0
fi

cd $basePath
cp $basePath/.bash_profile  $basePath/.bash_profile.$time
cp $srcPath/bash_profile.std  $basePath/.bash_profile
source .bash_profile


ret=check_dir_readable $SADIR
if [ 0 eq $ret]; then 
		return 0
fi
ret=check_dir_readable $BIPDIR
if [ 0 eq $ret]; then 
		return 0
fi
ret=check_dir_readable $TUXDIR
if [ 0 eq $ret]; then 
		return 0
fi



mv  $proPath $renameProPath
mv  $caPath  $renameCaPath


cd 

