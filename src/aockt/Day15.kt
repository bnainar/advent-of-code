package aockt

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

object Day15 {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val path = "C:/dev/advent-of-code/inputs/t"
        val lines = Files.readString(Paths.get(path)).split(",".toRegex()).toTypedArray()
        part1(lines)
        part2(lines)
    }

    private fun getHash(l: String): Int {
        return l.chars().reduce(0) { acc: Int, c: Int -> (acc + c) * 17 % 256 }
    }

    private fun part1(lines: Array<String>) {
        println("Part 1: " + lines.sumOf { getHash(it) })
    }

    private fun part2(lines: Array<String>) {
        val boxes = Array(256) { mutableListOf<Lens>() }
        for (line in lines) {
            var op = line.indexOf('-')
            if (op == -1) op = line.indexOf('=')
            val label = line.substring(0, op)
            val h = getHash(label)
            if (line[op] == '-') {
                boxes[h].removeIf { it.label == label }
            } else {
                val newLens = Lens(label, line[op + 1].code - '0'.code)
                val idx = boxes[h].indexOfFirst { it.label == label }
                if (idx == -1)
                    boxes[h].add(newLens)
                else
                    boxes[h][idx] = newLens
            }
        }
        var ans = 0
        boxes.forEachIndexed { i, x ->
            x.forEachIndexed { j, y ->
                ans += (i + 1) * (j + 1) * y.focalLength
            }
        }
        println("Part 2: $ans")
    }

    internal data class Lens(val label: String, val focalLength: Int)
}