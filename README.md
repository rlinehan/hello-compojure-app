# hello

Simple example compojure app, now running with [trapperkeeper](https://github.com/puppetlabs/trapperkeeper).

## Prerequisites

You will need [Leiningen][1] 1.7.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

To run with trapperkeeper, run:

    lein trampoline run --config config.ini --bootstrap-config bootstrap.cfg

## License

Copyright © 2014 Ruth L. Linehan
