### metapasta demo project

it's strongly recommended to launch *metapasta* projects from EC2 instances because for publishing they need upload around 100MB to S3, that can be slow on local machine.


* install cs: `curl https://raw.githubusercontent.com/n8han/conscript/master/setup.sh | sh`
* install nispero super cli: `cs ohnosequences/nisperoCLI -b super-cli` or `~/bin/cs ohnosequences/nisperoCLI -b super-cli` or [manually](https://github.com/ohnosequences/nisperoCLI/blob/master/doc/installation.md)
* create project: `nispero create ohnosequences/metapasta` or `~/bin/nispero create ohnosequences/metapasta`
* put samples in [configuration](https://github.com/ohnosequences/metapasta-projects/blob/master/e7p/src/main/scala/configuration.scala)
* publish it: `sbt publish`
* run it: `sbt "run run"`

> on big samples most likely user have to perform further adhoc adjustments: map workers group, throughput of DinamoDB tables.
