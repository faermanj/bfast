package jfaerman.bfast.cache;

import org.infinispan.config.ConfigurationBeanVisitor;
import org.infinispan.loaders.CacheLoaderConfig;


public class OrderLoaderConfig  implements CacheLoaderConfig{

  @Override
  public void accept(ConfigurationBeanVisitor visitor) {
    
  }

  @Override
  public String getCacheLoaderClassName() {
    return OrderLoader.class.getName();
  }

  @Override
  public void setCacheLoaderClassName(String s) {
  }

  @Override
  public ClassLoader getClassLoader() {
    return getClassLoader();
  }
  
  @Override
  public CacheLoaderConfig clone(){
    return new OrderLoaderConfig();
  }
  
}