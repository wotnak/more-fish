rootProject.name = "Rby"
include("Rby")
include("RbyApi")
include("RbyAddons")

project(":Rby").projectDir = File("plugin")
project(":RbyApi").projectDir = File("api")
project(":RbyAddons").projectDir = File("addons")
