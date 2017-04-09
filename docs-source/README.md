## Documentation

The documentation has been written on [asciidoctor](http://asciidoctor.org/) tool.

### Writing

The best way how to setup the tool for writing documentation has been described on the [asciidoctor documentation](http://asciidoctor.org/docs/editing-asciidoc-with-live-preview/).

The guard configuration (`Guardfile`) mirror the following ascidoctor command:

`asciidoctor index.adoc -a linkcss`

To start watching the file run guard by the following command:

`guard start`

### Deploy documentation

To deploy documentation to the GitHub pages copy generated `index.html` to `docs` directory.

### Use different stylesheet

By default the `asciidoctor.css` and `coderay-asciidoctor.css` are used.

In order different stylesheet for documentation the following command has to be used:

`asciidoctor index.adoc -a linkcss -a stylesheet=foundation.css`

### Other links

##### Asciidoctor User manual:

http://asciidoctor.org/docs/user-manual/

##### Stylesheet factory:

https://github.com/asciidoctor/asciidoctor-stylesheet-factory
