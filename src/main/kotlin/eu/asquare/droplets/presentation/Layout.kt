package eu.asquare.droplets.presentation

import com.samskivert.mustache.Mustache
import com.samskivert.mustache.Template
import java.io.Writer

class Layout : Mustache.Lambda {
    var body = ""

    override fun execute(frag: Template.Fragment?, out: Writer?) {
        body = frag?.execute() ?: ""
    }
}