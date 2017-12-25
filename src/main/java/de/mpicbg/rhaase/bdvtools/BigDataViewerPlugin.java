package de.mpicbg.rhaase.bdvtools;

import bdv.BigDataViewer;
import org.scijava.plugin.SciJavaPlugin;
import org.scijava.plugin.SingletonPlugin;

/**
 * Author: Robert Haase (http://haesleinhuepf.net) at MPI CBG (http://mpi-cbg.de)
 * December 2017
 */
public interface BigDataViewerPlugin extends SciJavaPlugin,
                                             SingletonPlugin
{
  void setBdv(BigDataViewer bdv);
  void run();
}
