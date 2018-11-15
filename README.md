Axon Framework configuration example for CDI (Java EE / Jakarta EE)
==============

This project demonstrates, how "discover-types-cdi" can be used to configure axon framework
inside a Java EE / Jakarta EE project. 

It also demonstrates,
how the domain code could be decoupled as much as possible from axon framework (Version 3.3.x).

Axon framework is written very modular and does not need
to be integrated inside a Java EE Project using an "all inclusive" CDI extension.
Although it may be practical to do it that way and let it look similar to spring,
it is not necessary to do so.
Producing all kinds of beans and therefore publishing every bit of the configuration
to be called from theoretically everywhere in the domain code can cause problems.

# Hexagonal Architecture
The idea behind this is inspired by "hexagonal architecture" or "ports and adapters".
The "infrastructure"-package contains the port (or "boundary") to the domain and
is implemented by an adapter to connect axon. 

If the domain code, for example, wants to send an domain event, 
it uses the locally defined ubiquitous language represented by internal services (e.g. ExampleEventBusService, not axon directly), 
which delegate the request to axon framework (axon framework event bus).

To decouple it further and get it closer to "hexagonal architecture", 
"ExampleEventBusService" (and alike) would be changed to interfaces and their implementation
(axon framework adapters) would be placed in a different module. 

Since it is not planned to e.g. introduce an axon mockup for integration testing or get
completely rid of the build dependencies (so that really nobody can reach for axon framework dependencies directly in the domain module), these steps are skipped for now.
Further complexity should only be introduced when needed.
