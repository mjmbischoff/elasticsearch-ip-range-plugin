# ip range plugin 

## problem description

Currently, you can use special markers in your configuration for the public address (`_local_`, `_site_`, `_public_`, 
`_[network-interface-name]_` etc) In general this works well. However, in the example where we split between http and 
transport both interfaces can be of type `_site_` not allowing us to specify intent. We could use network interface 
names but this requires extra steps to correlate. On Windows relying on the (artificial) network interface can be 
problematic as they might change between restarts.

If we have an interface on 10.0.0.x and one on 192.168.0.x and we want to bind one to http, and the other to transport
a configuration of `http.host: _range:10.0.0.0/24_` specifies our intent more clearly.

This plugin tries to achieve just that.

## Obtaining and installing the plugin

### build the zip

```bash
mvn package
```
this should result in a zip file in under the `target/` directory.

Notes: plugins are somewhat tied to elasticsearch versions, you might need to update the following part of pom.xml to match your version:
```xml
    <properties>
        <!-- which version of the Elastic Stack to build against/target -->
        <elastic.version>7.13.2</elastic.version>
    </properties>
```

### Installing

Please note that `elasticsearch-plugin` expects an url so use `elasticsearch-plugin install file://`[absolute path to zip]