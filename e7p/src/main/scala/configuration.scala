package e7p

import ohnosequences.awstools.s3.ObjectAddress
import ohnosequences.nisperon.bundles.NisperonMetadataBuilder
import ohnosequences.metapasta._
import ohnosequences.awstools.autoscaling._
import ohnosequences.nisperon._
import ohnosequences.awstools.ec2._

object mockSamples {
  val testDir = ObjectAddress("resources.ohnosequences.com/sequences/metagenomics-test")


  val s1 = ObjectAddress(
    "E7P1",
    testDir / "E7P1_S93_L001_R1_001.fastq.gz",
    testDir / "E7P1_S93_L001_R2_001.fastq.gz"
  )
  
  val s2 = ObjectAddress(
    "E7P6",
    testDir / "E7P6_S93_L001_R1_001.fastq.gz",
    testDir / "E7P6_S93_L001_R2_001.fastq.gz"
  )    

  val samples = List(s1, s2)
}

object configuration extends BlastConfiguration (
  metadataBuilder = new NisperonMetadataBuilder(new generated.metadata.e7p()),
  email = "museeer@gmail.com",
  password = "1243b4ad0f",
  mappingWorkers = Group(size = 1, max = 20, instanceType = InstanceType.T1Micro, purchaseModel = OnDemand),
  uploadWorkers = None,
  samples = mockSamples.samples,
  logging = true,
  blastTemplate = """blastn -task megablast -db $db$ -query $input$ -out $output$ -max_target_seqs 1 -num_threads 1 -outfmt $out_format$ -show_gis""",
  xmlOutput = false
)

object e7p extends Metapasta(configuration) {


}
