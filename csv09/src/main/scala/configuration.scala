package csv09

import ohnosequences.awstools.s3.ObjectAddress
import ohnosequences.nisperon.bundles.NisperonMetadataBuilder
import ohnosequences.metapasta._
import ohnosequences.awstools.autoscaling._
import ohnosequences.nisperon._
import ohnosequences.awstools.ec2._
import ohnosequences.metapasta.reporting._

object mockSamples {
  val testBucket = "metapasta-test"

  val ss1 = "supermock3"
  val s1 = PairedSample(ss1, ObjectAddress(testBucket, "mock/" + ss1 + ".fastq"), ObjectAddress(testBucket, "mock/" + ss1 + ".fastq"))

  val s2 = PairedSample("supermock33", ObjectAddress(testBucket, "mock/" + ss1 + ".fastq"), ObjectAddress(testBucket, "mock/" + ss1 + ".fastq"))
  val t1 = SampleTag("t1")
  val samples = List(s1, s2)

  val tagging = Map(s1 -> List(t1))

}

object configuration extends BlastConfiguration (
  metadataBuilder = new NisperonMetadataBuilder(new generated.metadata.csv09()),
  email = "museeer@gmail.com",
  password = "9de4361ef",
  mappingWorkers = Group(size = 1, max = 20, instanceType = InstanceType.T1Micro, purchaseModel = OnDemand),
  uploadWorkers = None,
  samples = mockSamples.samples,
  tagging = mockSamples.tagging,
  logging = true,
  defaultInstanceSpecs =NisperonConfiguration.defaultInstanceSpecs.copy(instanceProfile = Some("nispero")),
  blastTemplate = """blastn -task megablast -db $db$ -query $input$ -out $output$ -max_target_seqs 1 -num_threads 1 -outfmt $out_format$ -show_gis""",
  xmlOutput = false,
  assignmentConfiguration = AssignmentConfiguration(400, 0.8)
)

object csv09 extends Metapasta(configuration) {


}
