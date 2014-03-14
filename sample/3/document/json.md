
<table class="table table-bordered table-striped table-condensed">
   <tr>
	<th></th>
	<th width="12%">MOXy</th>
	<th width="14%">JSON-P<br/>[JSR 353]</th>
	<th width="12%">Jackson</th>
	<th width="12%">Jettison</th></tr>
   <tr>
      <td>POJO-based JSON Binding</td>
      <td>Yes</td>
      <td>No</td>
      <td>Yes</td>
      <td>No</td>
   </tr>
   <tr>
      <td>JAXB-based JSON Binding</td>
      <td>Yes</td>
      <td>No</td>
      <td>Yes</td>
      <td>Yes</td>
   </tr>
   <tr>
      <td>Low-level JSON parsing & processing</td>
      <td>No</td>
      <td>Yes</td>
      <td>No</td>
      <td>Yes</td>
   </tr>
</table>


## moxy ##

	<dependency>
		<groupId>org.glassfish.jersey.media</groupId>
		<artifactId>jersey-media-moxy</artifactId>
	</dependency>

- MoxyJsonConfig

## jackson ##

	<dependency>
		<groupId>org.glassfish.jersey.media</groupId>
		<artifactId>jersey-media-json-jackson</artifactId>
	</dependency>

- JacksonFeature
- ContextResolver

## jettison ##

	<dependency>
		<groupId>org.glassfish.jersey.media</groupId>
		<artifactId>jersey-media-json-jettison</artifactId>
	</dependency>

- JettisonFeature

## json-p ##

	<dependency>
		<groupId>org.glassfish.jersey.media</groupId>
		<artifactId>jersey-media-json-processing</artifactId>
	</dependency>