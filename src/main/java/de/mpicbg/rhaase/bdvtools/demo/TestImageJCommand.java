package de.mpicbg.rhaase.bdvtools.demo;

import bdv.BigDataViewer;
import bdv.util.BdvFunctions;
import bdv.util.BdvHandleFrame;
import bdv.util.BdvStackSource;
import de.mpicbg.rhaase.bdvtools.BigDataViewerPluginService;
import net.imglib2.img.Img;
import net.imglib2.img.array.ArrayImgs;
import net.imglib2.type.numeric.real.FloatType;
import org.scijava.command.Command;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

/**
 * Author: Robert Haase (http://haesleinhuepf.net) at MPI CBG (http://mpi-cbg.de)
 * December 2017
 */
//@Plugin(type = Command.class)
public class TestImageJCommand implements Command
{
  @Parameter
  BigDataViewerPluginService bigDataViewerPluginService;

  @Override public void run()
  {

    Img<FloatType> img = ArrayImgs.floats(new long[]{ 100, 100, 100});

    BdvStackSource<FloatType> bdvStackSource = BdvFunctions.show(img, "Test");
    if (bdvStackSource.getBdvHandle() instanceof BdvHandleFrame){

      bigDataViewerPluginService.injectPlugins(((BdvHandleFrame) bdvStackSource.getBdvHandle()).getBigDataViewer());
    }
  }
}
