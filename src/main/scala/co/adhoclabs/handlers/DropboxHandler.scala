package co.adhoclabs.handlers

import net.jcazevedo.moultingyaml.{YamlString, _}

import scala.io.Source

trait DropboxHandler {
  private val config = getClass.getResource("/config.yaml").getPath
  private val yamlAst = Source.fromFile(config).mkString.parseYaml

  protected val saveEndpointUrl = "https://api.dropbox.com/1/save_url/auto/"
  protected val metadataEndpointUrl = "https://api.dropboxapi.com/1/metadata/auto/"

  val token = yamlAst.asYamlObject.fields.get(YamlString("dropbox-token")).get.prettyPrint.replaceAll("\n", "")
  val folder = yamlAst.asYamlObject.fields.get(YamlString("dropbox-folder")).getOrElse(YamlString("/")).prettyPrint.replaceAll("\n", "")
}
