import SwiftUI
import Lottie
import Shared

@main
struct iOSApp: App {
    
    init() {
        
        LottieSplashHelper.shared.lottieViewFactory = { jsonContent, onFinished in
            guard let data = jsonContent.data(using: .utf8),
                  let animation = try? LottieAnimation.from(data: data) else {
                return UIView()
            }
            
            let animationView = LottieAnimationView(animation: animation)
            animationView.contentMode = .scaleAspectFit
            animationView.play { completed in
                if completed {
                    onFinished()
                }
            }
            return animationView
        }
    }

    var body: some Scene {
        
        WindowGroup {
            MainView()
        }
    }
}
