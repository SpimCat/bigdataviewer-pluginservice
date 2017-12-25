package de.mpicbg.rhaase.bdvtools.demo;

import bdv.BigDataViewer;
import bdv.util.Bdv;
import bdv.util.BdvFunctions;
import bdv.util.BdvHandle;
import bdv.util.BdvStackSource;
import de.mpicbg.rhaase.bdvtools.BigDataViewerPlugin;
import net.imagej.ImageJ;
import net.imglib2.img.Img;
import net.imglib2.img.array.ArrayImgs;
import net.imglib2.type.numeric.real.FloatType;
import org.scijava.plugin.Plugin;

/**
 * Author: Robert Haase (http://haesleinhuepf.net) at MPI CBG (http://mpi-cbg.de)
 * December 2017
 */
//@Plugin(type = BigDataViewerPlugin.class)
public class TestBDVPlugin implements BigDataViewerPlugin
{
  BigDataViewer bdv;
  @Override public void setBdv(BigDataViewer bdv)
  {
    this.bdv = bdv;
  }

  @Override public void run()
  {
    System.out.println("BDV: " + bdv);
  }

  public static void main(String... args) {
    ImageJ ij = new ImageJ();

    ij.command().run(TestImageJCommand.class, true, new Object[]{});
  }
}
