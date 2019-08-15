#-------------------------sa----------------------------------- 
export SADIR=${saConf}
export LD_LIBRAY_PATH=/opt/mqm/lib64;$SADIR/lib64;$LD_LIBRARY_PATH
export CLASSPATH=$SADIR/lib64;$CLASSPATH

#-------------------------BIP----------------------------------- 
export BIPDIR=${bipConf}

#-------------------------tuxedo----------------------------------- 
export TUXDIR=${tuxConf}
