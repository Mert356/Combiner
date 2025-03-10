import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Frame: ImageVector
	get() {
		if (_Frame != null) {
			return _Frame!!
		}
		_Frame = ImageVector.Builder(
            name = "Frame",
            defaultWidth = 20.dp,
            defaultHeight = 20.dp,
            viewportWidth = 15f,
            viewportHeight = 15f,
        ).apply {
			path(
    			fill = SolidColor(Color(0xFF000000)),
    			fillAlpha = 1.0f,
    			stroke = null,
    			strokeAlpha = 1.0f,
    			strokeLineWidth = 1.0f,
    			strokeLineCap = StrokeCap.Butt,
    			strokeLineJoin = StrokeJoin.Miter,
    			strokeLineMiter = 1.0f,
    			pathFillType = PathFillType.EvenOdd
			) {
				moveTo(11f, 1.5f)
				curveTo(11f, 1.2239f, 10.7761f, 1f, 10.5f, 1f)
				curveTo(10.2239f, 1f, 10f, 1.2239f, 10f, 1.5f)
				verticalLineTo(4f)
				horizontalLineTo(5f)
				verticalLineTo(1.5f)
				curveTo(5f, 1.2239f, 4.7761f, 1f, 4.5f, 1f)
				curveTo(4.2239f, 1f, 4f, 1.2239f, 4f, 1.5f)
				verticalLineTo(4f)
				horizontalLineTo(1.5f)
				curveTo(1.2239f, 4f, 1f, 4.2239f, 1f, 4.5f)
				curveTo(1f, 4.7761f, 1.2239f, 5f, 1.5f, 5f)
				horizontalLineTo(4f)
				verticalLineTo(10f)
				horizontalLineTo(1.5f)
				curveTo(1.2239f, 10f, 1f, 10.2239f, 1f, 10.5f)
				curveTo(1f, 10.7761f, 1.2239f, 11f, 1.5f, 11f)
				horizontalLineTo(4f)
				verticalLineTo(13.5f)
				curveTo(4f, 13.7761f, 4.2239f, 14f, 4.5f, 14f)
				curveTo(4.7761f, 14f, 5f, 13.7761f, 5f, 13.5f)
				verticalLineTo(11f)
				horizontalLineTo(10f)
				verticalLineTo(13.5f)
				curveTo(10f, 13.7761f, 10.2239f, 14f, 10.5f, 14f)
				curveTo(10.7761f, 14f, 11f, 13.7761f, 11f, 13.5f)
				verticalLineTo(11f)
				horizontalLineTo(13.5f)
				curveTo(13.7761f, 11f, 14f, 10.7761f, 14f, 10.5f)
				curveTo(14f, 10.2239f, 13.7761f, 10f, 13.5f, 10f)
				horizontalLineTo(11f)
				verticalLineTo(5f)
				horizontalLineTo(13.5f)
				curveTo(13.7761f, 5f, 14f, 4.7761f, 14f, 4.5f)
				curveTo(14f, 4.2239f, 13.7761f, 4f, 13.5f, 4f)
				horizontalLineTo(11f)
				verticalLineTo(1.5f)
				close()
				moveTo(10f, 10f)
				verticalLineTo(5f)
				horizontalLineTo(5f)
				verticalLineTo(10f)
				horizontalLineTo(10f)
				close()
			}
		}.build()
		return _Frame!!
	}

private var _Frame: ImageVector? = null
