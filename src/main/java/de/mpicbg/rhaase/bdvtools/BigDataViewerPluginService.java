package de.mpicbg.rhaase.bdvtools;

import bdv.BigDataViewer;
import bdv.util.BdvHandle;
import org.scijava.InstantiableException;
import org.scijava.plugin.AbstractPTService;
import org.scijava.plugin.AbstractSingletonService;
import org.scijava.plugin.Plugin;
import org.scijava.plugin.PluginInfo;
import org.scijava.service.SciJavaService;
import org.scijava.service.Service;

import javax.swing.*;

/**
 * Author: Robert Haase (http://haesleinhuepf.net) at MPI CBG (http://mpi-cbg.de)
 * December 2017
 */

@Plugin(type = Service.class)
public class BigDataViewerPluginService extends
                                        AbstractSingletonService<BigDataViewerPlugin> implements
                                                                                      SciJavaService
{

  @Override public Class<BigDataViewerPlugin> getPluginType()
  {
    return BigDataViewerPlugin.class;
  }

  public void injectPlugins(BigDataViewer bdv)
  {
    JMenu pluginMenu = new JMenu("Plugins");

    for (PluginInfo<BigDataViewerPlugin> pluginInfo : getPlugins()) {
      JMenuItem menuItem = new JMenuItem(pluginInfo.getClassName());
      menuItem.addActionListener((actionEvent) -> {

        try
        {
          BigDataViewerPlugin plugin = pluginInfo.createInstance();
          plugin.setBdv(bdv);
          plugin.run();
        }
        catch (InstantiableException e)
        {
          e.printStackTrace();
        }

      });

      pluginMenu.add(menuItem);

    }
    bdv.getViewerFrame().getJMenuBar().add(pluginMenu);
    JButton button = new JButton("Plugins");
    button.setLocation(200, 0);
    button.setSize(200, 50);
    button.addActionListener((actionEvent) -> {
      pluginMenu.getPopupMenu().show(bdv.getViewerFrame(), 200, 50);
    });

    bdv.getViewerFrame().getViewerPanel().add(button);

    // this is to actually show the new menu entries
    bdv.getViewerFrame().setJMenuBar(bdv.getViewerFrame().getJMenuBar());
  }
}
